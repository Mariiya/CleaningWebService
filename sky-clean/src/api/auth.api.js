const baseUrl = 'http://localhost:8888'

export const getAccessToken = async (data, url) => {
  const request = await fetch(`${baseUrl}${url}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return request
}

export const addNewUser = async (data, url) => {
  const request = await fetch(`${baseUrl}${url}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return request
}