<template>
  <view class="container">

    <scroll-view class="scroll">
      <view class="header">
        <view class="logo-container">
        <image class='logo'
          :source="{uri: baseURL + '/static/image/jpg?path=resources/white_logo_entraide.png'}"
        />
        </view>
        <text class="title">{{titre}}</text>
      </view>

      <view class="main">
        <image class='image'
          :source="{uri: baseURL + '/static/image/jpg?path=' + imageChallenge}"
        />
        <text class="description">{{description}}</text>
        <button :on-press="accessChallengeValidation"
          title="Valider ce challenge"
          color="#841584"
          accessibility-label="Accéder à la validation du défi"
        />
      </view>

      <view class="commentaires" v-for="(commentaire, index) in commentaires" :key="index">
        <text>{{commentaire}}</text>
      </view>
    </scroll-view>

  </view>
</template>


<style>
.container {
  background-color: #b2ebcc;
  align-items: center;
  justify-content: center;
  flex: 1;
  width:100%;
}

.scroll {
  width:100%;
  height:100%;
}

.header{
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 80%;
  height:25%;
  min-height: 90;
  background-color: #3d9d84;
  border-bottom-right-radius: 15;
  margin-bottom:20;
}

.logo-container{
  width:30%;
  align-items: center;
}

.logo{
    /* transform: rotate(30deg); */
    width : 80;
    height : 80;
}

.title{
    color : white ; /*#1d3060*/
    font-size:30;
    font-weight: 200;
    width: 70%;
    text-align: center;
}

.main{
  height:100%;
  background-color: grey;
}
.image{
    width : 300;
    height: 250;
}


.description{
    color : #2c3c74;
    font-size:20;
}

.commentaires{
  color :#1d3060;
  background-color: red;;
}

</style>


<script>
import {request, baseURL} from '../../api.js';
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
        description:'',
        categoryId:'',
        imageCategory:'',
        imageChallenge:'',
        commentaires:[],
        baseURL:baseURL,
    }
  },
  methods: {

    accessChallengeValidation : function(){
        this.navigation.navigate("Validation", {challengeId:this.navigation.state.params.challengeId})
    },

    fetch : function() {
      const self = this;
       request({
        method: 'get',
        url: '/api/getChallenge/'+this.navigation.state.params.challengeId
        }).then(function(response){
          self.titre = response.data.name;
          self.description = response.data.description;
          self.categoryId =response.data.categoryId;
          self.imageChallenge= response.data.imgPath;
          self.getComments();
        }).catch(function(error){
          console.log(error);
        })
    },

    getComments : function(){
      const self = this;
       request({
        method: 'get',
        url: '/api/getCommentsOfChallenge/'+this.navigation.state.params.challengeId
        }).then(function(response){
          self.commentaires =response.data;
          console.log(response.data)
          self.getImageCategory();
        }).catch(function(error){
          console.log(error);
        })
    },

    getImageCategory : function(){
      const self = this;
       request({
        method: 'get',
        url: '/api/getCategory/'+self.categoryId
        }).then(function(response){
          self.imageCategory =response.data.logo;
          // console.log(response)
        }).catch(function(error){
          console.log(error);
        })
    }


  },
  mounted : function(){
      this.fetch();
  }
};

</script>
