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
        <view class="image-container">
          <image class='image'
            :source="{uri: baseURL + '/static/image/jpg?path=' + imageChallenge}"
          />
        </view>
        <view class="desc-container">
          <text class="desc">{{description}}</text>
        </view>
        <touchable-opacity :on-press="accessChallengeValidation">
        <text class="validation-btn">Valider ce challenge</text>
        </touchable-opacity>
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
  height:100%;
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
  height:20%;
  min-height: 90;
  background-color: #3d9d84;
  border-bottom-right-radius: 15;
  /* margin-bottom:30; */
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
  height:150%;
  align-items: center;
}

.image-container{
  width: 100%;
  min-height: 30;
  align-items: center;
  margin-bottom:30;
  margin-top:30;
}
.image{
    width : 200;
    height: 200;
    border-width: 3;
    border-color: white;
    border-radius: 15;
}

.desc-container{
    width:100%;
    border-top-width: 2; 
    border-color:#3d9d84;
    max-width: 80%;
    justify-content: center;
    align-items: center;
    padding: 10;
    padding-top:30;
    margin-bottom:30;
}
.desc{
    color : #2c3c74;
    font-size:20;
    /* text-align: center; */
}

.validation-btn{
    font-size: 20;
    color: white;
    background-color:#3d9d84;
    border-radius: 50;
    padding-top: 10;
    padding-bottom: 10;
    padding-left: 20;
    padding-right: 20;
}

.commentaires{
  color :#1d3060;
  min-height: 100;
  width:100%;
  background-color: red;
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
