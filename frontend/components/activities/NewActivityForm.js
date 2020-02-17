import React, { Fragment, useState } from 'react';
import { Button, Input, Upload, Icon } from 'antd';

import { useInput } from '../../utils/hooks/useInput';
import { Margin } from '../Margin';
import { UPLOAD_URL } from '../../services/api';

export function NewActivityForm(props) {
  const [value, setValue] = useInput('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const [attachments, setAttachments] = useState([]);

  const handleSubmit = async () => {
    setIsSubmitting(true);
    await props.onSubmit(value, attachments);
    setIsSubmitting(false);

    setAttachments([]);
    setValue('');
  }

  const handleAttachmentChange = (info) => {
    if (info.file.status === 'done') {
      setAttachments([...attachments, info.file.response]);
    }

    if (info.file.status === 'removed') {
      setAttachments(attachments.splice(attachments.indexOf(info.file.response), 1));
    }
  }

  return (
    <Fragment>
      <Margin margin="0 0 8px">
        <Input.TextArea
          placeholder="Napiši nešto"
          value={value}
          onChange={setValue}
          autoSize={{
            minRows: 3,
            maxRows: 10,
          }}
        />
      </Margin>

      <Margin margin="0 0 8px">
        <Upload
          action={UPLOAD_URL}
          onChange={handleAttachmentChange}
          listType="picture"
        >
          <Button>
            <Icon type="upload" /> Dodaj medijski sadržaj
          </Button>
        </Upload>
      </Margin>

      <Button
        type="primary"
        size="large"
        disabled={!value}
        loading={isSubmitting}
        onClick={handleSubmit}
      >
        Stvori aktivnost
      </Button>

      <style jsx>{`
        textarea {
          width: 100%;
          padding: 10px;
          outline: none;
          border: 1px solid var(--theme-color);
          border-radius: 5px;
          height: 100px;
          font-size: 16px;
          font-family: inherit;
        }
      `}</style>
    </Fragment>
  )
}
