import { Input, Button } from 'antd';
import React, { Fragment } from 'react';

import { useInput } from '../../utils/hooks/useInput';
import { Margin } from '../Margin';

export function ActivityCommentForm({ onSubmit }) {
  const [value, setValue] = useInput('');

  const handleSubmit = () => {
    onSubmit(value);
    setValue('');
  }

  return (
    <Fragment>
      <Margin margin="0 0 8px">
        <Input.TextArea
          value={value}
          onChange={setValue}
        />
      </Margin>
      <Button
        onClick={handleSubmit}
      >
        Dodaj komentar
      </Button>
    </Fragment>
  )
}
