import { Result, Button } from "antd";
import Link from "next/link";
import React, { Component } from "react";

import { confirmUser } from "../../services/user";

export default class ConfirmationPage extends Component {
  static async getInitialProps(context) {
    const { token } = context.query;

    let success;
    try {
      await confirmUser(token);
      success = true;
    } catch {
      success = false;
    }

    return {
      success,
    };
  }

  render() {
    const {
      success,
    } = this.props;

    return success ? (
      <Result
        status="success"
        title="Uspješno ste potvrdili račun!"
        subTitle="Sada se možete prijaviti"
        extra={[
          <Link
            href="/login"
          >
            <Button type="primary" key="console">
              Prijava
            </Button> 
          </Link>
        ]}
      />
    ) : (
      <Result
        status="error"
        title="Nevažeći token"
        subTitle="Molimo pokušajte ponovno"
      />
    );
  }
}