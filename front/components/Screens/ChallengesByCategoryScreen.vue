<template>
  <view class="container">
    <view class ="header">
      <!-- <text class="heading">Catégorie : </text> -->
      <text class="title">{{categoryInfo.name}}</text>
    </view>
    <scroll-view class = "myScrollView">
          <view class = "element-border" v-for="(challenge, index) in challenges" :key="index">
            <touchable-opacity class = "element-container" :on-press="() => goToChallenge(challenge)">
              <view class ="challenge-text">
              <text class = "challenge-title">{{challenge.name}}</text> 
              <text class = "challenge-desc">{{challenge.description}}</text>
              </view>
              <view>
              <image class = "challenge-icon" :source="{uri: baseURL + '/static/image/jpg?path=' + challenge.imgPath}"/>
              </view>
            </touchable-opacity>
          </view>
    </scroll-view>
      </view>
</template>

<style>
.container {
    background-color: #b2ebcc;
    width:100%;
    flex:1;
}

.header {
  height : 10%;
  justify-content: center;
  align-items: center;
  /* background-color: #b2ebcc; */
      background-color: #3d9d84;
  color:white;
  flex-direction: row;
  justify-content: space-around;
}

.title {
  font-size: 40px;
  font-weight: 200;
  color: white;
}

.element-border {
  border-bottom-width: 1;
  border-color: gray;
  width: 100%;
  padding: 15;
}

.element-container {
  width: 100%;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.challenge-text{
  /* background-color: grey; */
  width:80%;
  
}
.challenge-title {
  font-size: 20;
  color: #3d9d84;
  padding-bottom:10;
}

.challenge-description {
  font-size : 10;
}

.challenge-icon {
  width: 60;
  height: 60;
  border-radius: 25;
}
</style>

<script>
import {request, baseURL} from '../../api.js';
import React from 'react';
import {Text} from 'react-native';
export default {
  props: {
      navigation: {
        type: Object
      }
    },
  data: function() {
    return {
      baseURL: baseURL,
        categoryInfo: {
          name:'',
        },
        challenges: [
           {
            id: 1,
            title : 'Prendre des nouvelles de ses voisins',
            description : "En ce moment, certains n'ont pas la chance d'avoir des proches près d'eux ...",
            image : 'https://www.materialui.co/materialIcons/communication/phone_black_192x192.png'
          },
           {
            id: 2,
            title : 'Faire une mousse au chocolat',
            description : "Des oeufs, une tablette de chocolat dessert? Pourquoi ne pas essayer de reproduire chez vous ...",
            image : 'https://icons-for-free.com/iconfiles/png/512/cake+48px-131987943060752100.png'
          },
           {
            id: 3,
            title : 'Un troisième challenge',
            description : "Lorem ipsum je n'ai pas d'idée, de toute façons personne ne lira cette description. PERSONNE LIS TES PAVEEEES",
            image : ''
          },
          {
            id: 1,
            title : 'Prendre des nouvelles de ses voisins',
            description : "En ce moment, certains n'ont pas la chance d'avoir des proches près d'eux ...",
            image : 'https://www.materialui.co/materialIcons/communication/phone_black_192x192.png'
          },
           {
            id: 2,
            title : 'Faire une mousse au chocolat',
            description : "Des oeufs, une tablette de chocolat dessert? Pourquoi ne pas essayer de reproduire chez vous ...",
            image : 'https://icons-for-free.com/iconfiles/png/512/cake+48px-131987943060752100.png'
          },
           {
            id: 3,
            title : 'Un troisième challenge',
            description : "Lorem ipsum je n'ai pas d'idée, de toute façons personne ne lira cette description. PERSONNE LIS TES PAVEEEES",
            image : ''
          },
          {
            id: 1,
            title : 'Prendre des nouvelles de ses voisins',
            description : "En ce moment, certains n'ont pas la chance d'avoir des proches près d'eux ...",
            image : 'https://www.materialui.co/materialIcons/communication/phone_black_192x192.png'
          },
           {
            id: 2,
            title : 'Faire une mousse au chocolat',
            description : "Des oeufs, une tablette de chocolat dessert? Pourquoi ne pas essayer de reproduire chez vous ...",
            image : 'https://icons-for-free.com/iconfiles/png/512/cake+48px-131987943060752100.png'
          },
        ]
    }
  },

  methods: {
    fetch : function() {
      const self = this;
       request({
        method: 'get',
        url: '/api/getChallengeByCategory/'+self.navigation.state.params.categoryId
        }).then(function(response){
          //console.log(response)
          self.challenges = response.data
        }).catch(function(error){
          console.log(error);
        })
    },

    fetchCatInfo : function() {
      const self = this;
      request({
        method: 'get',
        url: '/api/getCategory/'+self.navigation.state.params.categoryId
        }).then(function(response){
          //console.log(response)
          self.categoryInfo = response.data
        }).catch(function(error){
          console.log(error);
        })
    },

    goToChallenge : function(challenge) {
      this.navigation.navigate("ChallengeDetail", {challengeId:challenge.id})
    },

    goToCorona : function() {
      this.navigation.navigate("Covid19")
    }
  },
  mounted: function() {
    this.fetch();
    this.fetchCatInfo();
  }
};
</script>
