import { Input } from 'antd';
import { useRouter } from 'next/router';
import React from 'react';

import { useInput } from '../../utils/hooks/useInput';

export function Search() {
  const [value, setValue] = useInput();
  const router = useRouter();

  const handleSearch = async (value) => {
    await router.push('/search/[query]', `/search/${value}`);
    setValue('');
  }

  return (
    <Input.Search
      placeholder="Pretraži korisnike"
      onSearch={handleSearch}
      enterButton
      value={value}
      onChange={setValue}
    />
  );
}
