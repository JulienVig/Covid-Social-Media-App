<template>
    <view class="real-container">
      <view class ="container">
        <text class="text-container">Enter username :</text>
        <text-input class="input-container" v-model="username"/>
        <text class="text-container">Enter password :</text>
        <text-input class="input-container" v-model="password"/>
        <!-- <button :on-press="onPressEvent"
        title="Login"
        color="#841584"
        accessibility-label="Learn more about this purple button"/> -->
        <touchable-opacity class="login-container">
           <text  class="login-btn" :on-press="login">Login</text>
        </touchable-opacity>
        <!-- <button title="Change page" @press="goToPage2"/>

        <button title="View tabs" @press="goToTabNavigator"/> -->

      </view>
  </view>
</template>

<script>
import {API} from '../../api.js';
import { Alert } from 'react-native';
import axios from "axios";

export default {
    props: {
      navigation: {
        type: Object
      }
    },
  data: function() {
    return {
        username:'',
        password:''
    }
  },
  methods: {
    onPressEvent () {
      this.password = this.username;
      Alert.alert('oui', 'oui')
      console.log('test clicked')
    },
    login () {
     console.log("Login !")
      var bodyFormData = new FormData();
        bodyFormData.append('username', this.username);
        bodyFormData.append('password', this.password);
        const self = this;
       API({
        method: 'post',
        url: '/login',
        data: bodyFormData,
        headers: {'Content-Type': 'multipart/form-data' }
        }).then(function(response){
          console.log(response)
          if(response != undefined){
              self.navigation.navigate("Challenges")
          }
        }).catch(function(error){
          console.log(error)
        })
    },
    goToPage2 (){
        alert('Please login');
    },
    goToTabNavigator() {
      alert('Please login');
    }
  },
  mounted: function() {
      this.username = 'user'
      this.password = 'user'
  }
};
</script>

<style>
.real-container {
    align-items: center;
    justify-content: center;
    flex:1;
}
.container {
  background-color: white;
  width:80%;
}
.text-container{
    font-size: 22;
}
.input-container {
  border-style: solid;
  border-color: #FFFFFF;
  border-bottom-color: #888888;
  border-width: 2;
  font-size: 22;
  padding: 10;
  margin-bottom: 20;
}

.login-container{
    margin-top: 10;
    width: 30%;
    border-radius: 10;
}

.login-btn {
    padding: 20;
    width:100%;
    font-size: 22;
    background-color: #EEAAEE;
color:white;
}
</style>
