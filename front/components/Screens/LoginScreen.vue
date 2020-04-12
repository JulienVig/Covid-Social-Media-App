<template>
    <view class="real-container">
      <view class ="container">
        <text class="title">CONSVID la TÊTE</text>
        <text class="text-container">Pseudo :</text>
        <text-input class="input-container" placeholder="username" v-model="username"/>
        <text class="text-container">Mot de passe :</text>
        <text-input class="input-container" placeholder="password" secure-text-entry v-model="password"/>
        <view class="login-container">
           <text  class="login-btn" :on-press="login">Log in</text>
           <text  class="login-btn" :on-press="goToTabNavigator">Bypass login</text>
        </view>
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
           //console.log(response)
          if(response != undefined && response.status == 200){
              self.navigation.navigate("Défis")
          }
        }).catch(function(error){
           console.log(error)
        })
    },
    goToTabNavigator() {
      this.navigation.navigate("Défis")
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
    background-color : #f1b9a2;
}
.container {
  background-color :#f1b9a2;
  flex:1;
  width:80%;
}

.title{
    color : #980740;
    font-size: 40;
    font-weight: 200;
    top : 60;
    margin-bottom: 100;
}
.text-container{
    color : #e7794c;
    font-size: 22;
}
.input-container {
  border-style: solid;
  border-color:#e7794c;
  /*border-bottom-color: #888888;*/
  border-width: 2;
  font-size: 22;
  padding: 10;
  margin-bottom: 20;
}

.login-container{
    margin-top: 10;
    width: 100%;
    border-radius: 10;
    justify-content: space-between;
    flex-direction: row;


}

.login-btn {
    padding: 20;
    font-size: 22;
    background-color: #980740;
    color:white;
    
}
</style>
