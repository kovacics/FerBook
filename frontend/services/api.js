import fetch from 'isomorphic-fetch';

export const BASE_URL = 'https://ferbook.herokuapp.com/';
export const UPLOAD_URL = `${BASE_URL}file/upload`;

export async function request(endpoint, method, body, authenticationToken) {
  const response = await fetch(`${BASE_URL}${endpoint}`,{
    method,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': authenticationToken ? `Bearer ${authenticationToken}` : '',
    },
    body: body ? JSON.stringify(body) : null,
  });

  const contentType = response.headers.get('content-type');
  const isJson = contentType && contentType.indexOf("application/json") !== -1;

  if (isJson) {
    const json = await response.json();
    if (response.ok) {
      return json;
    } else {
      throw json;
    }
  } else if (!response.ok) {
    throw response;
  } else {
    return response.text();
  }
}
