const baseUrl = 'https://sky-clean-service.herokuapp.com'

export const get = async (url) => {
  const response = await fetch(`${baseUrl}${url}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })
  return await response.json();
}

export const post = async (url, data) => {
  const response = await fetch(`${baseUrl}${url}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return await response.json();
}

export const patch = async (url, data) => {
  const response = await fetch(`${baseUrl}${url}`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return await response.json();
}

export const put = async (url, data) => {
  const response = await fetch(`${baseUrl}${url}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return await response.json();
}

export const del = async (url, data) => {
  const response = await fetch(`${baseUrl}${url}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  return await response.json();
}