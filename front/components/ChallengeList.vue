<template>
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
</template>

<style >
.element-border {
  border-bottom-width: 1;
  border-color: gray;
  width: 100%;
  padding: 15;
}

.element-container {
  width: 85%;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width:100%;
  
}

.challenge-text {
 width: 80%;
}

.challenge-title {
  font-size: 20;
  color: #9E9E9E;
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
import {baseURL} from '../../api.js';

export default {
    props: ['challenges', "goToChallenge"],
    // methods: {
    //     goToChallenge : function(challenge) {
    //         this.navigation.navigate("ChallengeDetail", {challengeId:challenge.id})
    //     },
    // }
}
</script>