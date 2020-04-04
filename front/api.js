import axios from "axios";

 const API = axios.create({
  baseURL: `http://localhost:8080/`,
  withCredentials: true,
})

/*API.interceptors.response.use(function (response) {
    return response
  }, function (error) {
    if(!error.response){
      console.log("No response from server")
      return
    }
    if (error.response.data.status === 401) {
      console.log('access denied, disconnecting user')
    }
    return Promise.reject(error)
  })*/

  export default API