<template>
<scroll-view>
  <view class="container">
    <view class ="request-container" v-if="existFriendRequests">
      <text class="title" :style=styles.titlePrimaryColor>Demandes d'amis</text>
      <view class= "request-for" v-for="(request, index) in friendRequests" :key="index">
        <view class="request-elem" :style=styles.lightPrimaryColor>
          <text class="request-user" :style=styles.primaryTextColor>{{request.user}}</text>
          <view class="request-answer">
            <touchable-opacity class="request-accept" :on-press="(index) => answerRequest(index, true)">
              <text :style=styles.secondaryTextColor>Accepter</text>
            </touchable-opacity>
            <touchable-opacity class="request-decline" :on-press="(index) => answerRequest(index, false)">
              <text :style=styles.secondaryTextColor>Refuser</text>
            </touchable-opacity>
          </view>
          <!-- <AntDesign name="check" size="28" color="green"/>
          <Entypo name="cross" size="28" color="red"/> -->
        </view>
      </view> 
      <view class="sep" :style=styles.separatorColor></view>
    </view>

    <view class = "search-container">
      <text class="title" :style=styles.titlePrimaryColor>Chercher des amis</text>
      <view class="input-container">
        <text-input class = "input-text" 
        placeholder="Envoie une invitation à un amis !" 
        v-model= "searchPseudo"
        placeholderTextColor="grey"
        />
      </view>
      <touchable-opacity class="search-btn-container" :style=styles.defaultPrimaryColor>
        <text class="search-btn-text" :style=styles.textPrimaryColor>Demander en amis</text>
      </touchable-opacity>
      <view class="sep" :style=styles.separatorColor></view>
    </view>

    <view class ="friends-container" >
      <view class="friends-title-container">
        <text class="title" :style=styles.titlePrimaryColor>Liste des amis</text>
      </view>
      <view class= "friends-for" :style=styles.separatorColor>
        <view  v-for="(friend, index) in friends" :key="index">
          <touchable-opacity>
          <view class="friend-elem" :style=styles.lightPrimaryColor>
            <text class="friend-name" :style=styles.titlePrimaryColor>{{friend.name}}</text>
            <text class="friend-score" :style=styles.titlePrimaryColor>{{friend.score}}</text>
          </view>
          </touchable-opacity>
        </view>
      </view>
    </view>
  
  </view>
</scroll-view>
</template>

<style>
.container {
  background-color: white;
}
.request-container{
  width:100%;
 align-items: center;
}
.title {
  font-size: 25px;
  font-weight: bold;
  text-align: center;
  padding:20;
}

.request-for{
  width:80%;
}

.request-elem{
  justify-content: space-between;
  text-align: center;
  flex-direction: row;
  margin-bottom:10;
  /* border-color: #3d9d84; */
  /* border-width:2; */
  padding:10;
  
}

.request-answer{
  flex-direction: row;
}

.request-accept{
  color:white;
}
.request-decline{
  padding-left:10;
}

.sep{
  width: 60%;
  align-items: center;
  border-bottom-width: 2;
  margin: 20;
  margin-bottom:0;

}

.search-container{
  width:100%;
  justify-content: center;
  align-items: center;
}

.input-container{
  width:80%;
  min-height:30;
  border-color: #3d9d84;
  border-radius:5;
  border-width: 1;
  padding:10;
  margin-bottom:20;
}

.input-text {
  font-size: 18;
  text-align: left;
}

.search-btn-container{
  border-radius: 25;
  margin-bottom: 10;
}

.search-btn-text{
  font-size: 20;
    padding-top: 10;
    padding-bottom: 10;
    padding-left: 20;
    padding-right: 20;
}

.friends-container{
  width:100%;
  align-items: center;
}

.friends-for{
  width:85%;
  border-width: 2;
  border-radius:5;
  border-color:lightgrey;
  justify-content: center;
  align-items: center;
  padding:10;
  padding-top:20;
}

.friend-elem{
  flex-direction: row;
  justify-content: space-between;
  min-width: 100%;
  max-width:100%;
  border-radius:5;
  margin-bottom:10;
  padding:10;
}

.friend-name{

}

.friend-score{
  padding-left:10;
}


</style>

<script>
import {request} from '../../api.js';
import React from 'react';
import styles from "../../palette.js"
import { Entypo, AntDesign } from '@expo/vector-icons';

export default {
    props: {
        navigation: {
          type: Object
      }
  },
  components: {
    
  },
    data:   function() {
      return {
        styles:styles,
        searchPseudo: '',
        existFriendRequests: true,
        friendRequests: [
          {
            user: "Camarade 1"
          },
          {
            user: "Collègue 2"
          },
          {
            user: "Connaissance 3"
          }
        ],

        friends :[
          {
            name: "George",
            score: 3,
          },
          {
            name: "Georgette",
            score: 4,
          },
          {
            name: "Valentine",
            score: 2,
          },
          {
            name: "Valentin",
            score: 5,
          },
          {
            name: "xXPussySlayerXx",
            score: 1,
          },
          {
            name: "Claude",
            score: 6,
          },
          {
            name: "asdsadam,",
            score: 0,
          },
          {
            name: "1231",
            score: 5,
          },
          {
            name: "$à¨à$",
            score: 3,
          },
          {
            name: "______",
            score: 3,
          },


        ]
      }
    },
    methods:{
      answerRequest(index, accepted){
        // POST the answer here
        this.friendRequests.splice(index,1)
      }
    },
    mounted: function() {
      this.friends = this.friends.sort(function(a,b){
        return b.score - a.score;
      });
    }
  }
</script>
