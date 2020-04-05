<template>
  <view class="container">
      <image 
        class='fleche'
        :on-press="onPressRetour"
        :source="require('../../assets/images/defiscreen/fleche.png')"
      />

      <image
        class='hexagone'
        :source="require('../../assets/images/defiscreen/hexagone.png')"
      />
    <image
        class='licorne'
        :source="require('../../assets/images/defiscreen/licorne.png')"
      />

      <text class="titre">{{titre}}</text>

      <text class="description">{{description}}</text> 
  
  </view>
</template>


<style>
.container {
  background-color: #65D498;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.fleche{
    transform: rotate(180deg);
    width : 70;
    height : 70;
    position :relative;
    top : -160px;
    left : -170px;
    

}

.hexagone{
    transform: rotate(30deg);
    width : 80;
    height : 80;
    position :relative;
    top : -160px;
    left : -130px;

}
.licorne{
    width : 250;
    height : 150;
    position :relative;
    top : -140px;
}
.titre{
    color : #1d3060;
    font-size:25;
    position :relative;
    top : -370px;
    left : 50px;
}



.description{
    color : #1d3060;
    font-size:20;  
    top : -150px;
    left : -20px;
}

</style>


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
        titre:'',
        description:''
    }
  },
  methods: {
   /* login : function() {
      var bodyFormData = new FormData();
        bodyFormData.append('username', 'user');
        bodyFormData.append('password', 'user');
        const self = this;
       API({
        method: 'post',
        url: '/login',
        data: bodyFormData,
        headers: {'Content-Type': 'multipart/form-data' }
        }).then(function(response){
          console.log(response)
          self.request_challenge();
        })
    },*/

    fetch : function() {
      const self = this;
       API({
        method: 'get',
        url: '/api/getChallenge/'+this.navigation.state.params.challengeId
        }).then(function(response){
          self.titre = response.data.name;
          self.description = response.data.description;
        }).catch(function(error){

        })
    },

    onPressRetour: function() {
      Alert.alert('Clicked Image')
    },
  },

  mounted : function(){
      this.fetch();
  }

};

</script>
