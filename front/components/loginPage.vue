<template>
  <view class ="container">
    <text>Enter username :</text>
    <text-input>{{username}}</text-input>
    <text>Enter password :</text>
    <text-input>{{password}}</text-input>
    <button :on-press="onPressEvent"
    title="LE bouton a presser"
    color="#841584"
    accessibility-label="Learn more about this purple button"/>

    <button :on-press="login"
    title="Entrez seulement"
    color="#EEAAEE"
    accessibility-label="Learn more about this purple button"/>
</view>
</template>

<style>
.container {
  background-color: white;
  display: flex;
}
.text-container {
  color: blue;
  font-size: 22;
  width: 300;
  height:100;
  margin: auto;
}
</style>

<script>
import {API} from '../api.js';
import { Alert } from 'react-native';
import axios from "axios";
export default {
  data: function() {
    return {
        username:'',
        password:''
    }
  },
  methods: {
    onPressEvent : function() {
      this.password = this.username;
      Alert.alert('oui', 'oui')
      console.log('test clicked')
    },
    login : function() {
      /* API({
        method: 'post',
        url: '/login',
        data: {
          'username': this.username,
          'password': this.password
        },
        headers: {'Content-Type': 'multipart/form-data' }
        })*/
      axios.get('http://192.168.1.17:8080/allUsers').then(function(response){
        console.log('Response is : ')
        console.log(response.data)
        console.log('====================================')
      })
      .catch(function(error){
        if (!error.response) {
          console.log('No response from server')
          console.log(error)
          console.log('--------------------')
        } else {
          console.log(error.response.data)
          console.log(error.response.status)
          console.log(error.response.headers)
        }
      })
    }
  },
  mounted: function() {
    this.username = 'user'
    this.password = 'userPass'
  }
};
</script>
