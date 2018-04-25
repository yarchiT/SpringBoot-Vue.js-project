export const APIENDPOINT = "http://localhost:8181/api";

export const getHeader = function () {
  const tokenData = JSON.parse(window.localStorage.getItem('lbUser'))
  const headers = {
    'Accept':'application/json',
    'Authorization':'Bearer' + tokenData.token
  };
  return headers
};
