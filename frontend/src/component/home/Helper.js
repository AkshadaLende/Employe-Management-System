export function parseJwt(token) {
    if (!token) { return }
    const base64Url = token.split('.')[1]
    const base64 = base64Url.replace('-', '+').replace('_', '/')
    return JSON.parse(window.atob(base64))
  }

// import axios from 'axios';


// export const getAuthToken = () => {
//     return window.localStorage.getItem('auth_token');
// };

// export const setAuthHeader = (token) => {
//     window.localStorage.setItem('auth_token', token);
// };

// axios.defaults.baseURL = 'http://localhost:8080';
// axios.defaults.headers.post['Content-Type'] = 'application/json';

// export const request = (method, url, data) => {

//     let headers = {};
//     if (getAuthToken() !== null && getAuthToken() !== "null") {
//         headers = {'Authorization': `Bearer ${getAuthToken()}`};
//     }

//     return axios({
//         method: method,
//         url: url,
//         headers: headers,
//         data: data});
// };