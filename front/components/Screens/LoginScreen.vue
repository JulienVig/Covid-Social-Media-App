<template>
    <view class="real-container">
      <view class ="container">
        <text>Enter username :</text>
        <text-input v-model="username"/>
        <text>Enter password :</text>
        <text-input v-model="password"/>
        <button :on-press="onPressEvent"
        title="LE bouton a presser"
        color="#841584"
        accessibility-label="Learn more about this purple button"/>

        <button @press="login" title="Entrez seulement" color="#EEAAEE"/>
        <button title="Change page" @press="goToPage2"/>

        <button title="View tabs" @press="goToTabNavigator"/>

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
          self.navigation.navigate("Challenges")
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
