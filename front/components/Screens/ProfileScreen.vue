<!-- PROFILE SCREEN -->
<template>
    <view> 
        
        <scroll-view :content-container-style="{contentContainer: {
        paddingVertical: 20
        }}">

            <view> 
                <touchable-opacity :on-press="onPressButton">
                    <image
                        class="pp"
                        :source="require('../../assets/images/profilescreen/Icon-round-Question_mark.png')"
                    />
                </touchable-opacity>
                <text class="heading">{{username}}</text>
            </view>
        
            <view class="container">
                <text class= "heading">{{nbCompleted}} Défi relevé!</text>
                <text class="heading">Le Coeur sur la main</text>
    
              
                <view v-for="ach in coeurStats">
                    <image 
                    class="icon-achieved"
                    v-if="ach.pic == 1"
                    :source="require('../../assets/images/profilescreen/coeur_clair.png')"/>
                    <image 
                    class="icon-failed"
                    v-else
                    :source="require('../../assets/images/profilescreen/coeur_fonce.png')"/>
                    
                </view>
            
            
                
                <text class="heading">Sportif de haut niveau!</text>
                
                <view v-for="ach in sportStats">
                    <image 
                    class="icon-achieved"
                    v-if="ach.pic == 1"
                    :source="require('../../assets/images/profilescreen/cible_clair.png')"/>
                    <image 
                    class="icon-failed"
                    v-else
                    :source="require('../../assets/images/profilescreen/cible_fonce.png')"/>
                    
                </view>
            
                
                <text class="heading">Maniaco manie</text>
            
                <view v-for="ach in maniacStats">
                    <image 
                    class="icon-achieved"
                    v-if="ach.pic==1"
                    
                    :source="require('../../assets/images/profilescreen/maniac_clair.png')"/>
                    <image
                    class="icon-failed" 
                    v-else
                    :source="require('../../assets/images/profilescreen/maniac_fonce.png')"/>
                    
                    
                </view>
        
                
            </view>

        </scroll-view>
        
    </view>
</template>


<style>
.icon-achieved {
    height: 30;
    width: 30;
    background-color:green;
}
.icon-failed {
    height: 30;
    width: 30;
    background-color:red;
}
.pp{
    height: 120;
    width: 120;
}
.root{
    align-items: center;
    background-color: blue;
}
.container {
  align-items: center;
  justify-content: center;
  flex: 1;
}
.heading {
  font-size: 20;
  font-weight: bold;
  color: #980740;
  margin: 20;
}

.container2 {
    align-items: center;
    justify-content: center;
    background-color: blue;
    flex: 1;
}
</style>

<script>
import {API} from '../../api.js';
import { Alert } from 'react-native';
import axios from "axios";
    export default {
        data: function() {
            return{
                username:'John Doe'
            };
        },
        methods: {
            onPressButton () {
                alert(this.username + '\'s photo')
            },   

            login : function(){
                console.log("computing the coeur stats")
                    var bodyFormData = new FormData();
                    bodyFormData.append('username', 'user');
                    bodyFormData.append('password', 'user');
                    const self = this 
                    API({
                        method: 'post',
                        url: '/login',
                        data: bodyFormData,
                        headers: {'Content-Type': 'multipart/form-data' }
                        }).then(function(response){
                            console.log(response)
                            
                        })
            }
        },
        computed : {
            nbAchievedCoeur: {
                get: function() {
                    console.log("getting the number of achieved COEUR done")
                    return 3;
                }
            },

            nbAchievedSport: {
                get: function() {
                    console.log("getting the number of achieved SPORTS")
                    return 4;
                }
            },

            nbAchievedManiac: {
                get: function() {
                    console.log("getting the number of achieved MANIAC")
                    return 4;
                }
            },

            coeurStats: {
                get: function() {
                    this.login()
                    API({
                        method: 'GET',
                        url: "/getChallengeByCategory?category=coeur",
                        header: []
                    }).then(function(response){
                        console.log("recieved the coeur stats from the server")
                        var coeur = this.nbAchievedCoeur
                        console.log("ACHIEVED COEUR = "+ coeur)
                        var ach = []
                        var i
                        var total = response.data.length
                        for(i = 0; i < total;  ++i){
                            if(i < coeur){
                                ach.push({id: i, pic: 1})
                            } else {
                                ach.push({id: i, pic: 0})
                            }
                        }
                        return ach; 
                    })   
                }
            },

            sportStats: {
                get: function() {
                    this.login()
                    console.log("computing the sports stats")
                    API({
                        method: 'GET',
                        url: "/getChallengeByCategory?category=sport",
                        header: []
                    }).then(function(response){
                        console.log("recieved the sports stats from the server")
                        totalDefi = response.data.length
                        var ach = []
                        var sport = this.nbAchievedSport
                        var totalDefi = 6
                        var i
                        for(i = 0; i < totalDefi;  ++i){
                            if(i < sport){
                                ach.push({id: i, pic: 1})
                            } else {
                                ach.push({id: i, pic: 0})
                            }
                        }
                        return ach; 
                   }) 
                }
            },

            maniacStats: {
                get: function(){
                    this.login()
                    console.log("computing the maniac stats")
                    API({
                        method: 'GET',
                        url: "/getChallengeByCategory?category=sport",
                        hearder: []
                    }).then(function(response){
                        console.log("=======================> THE RESPONSE TO CATEGORY SPORT <===================")
                        console.log(response)
                        totalDefi = response.data.length
                        var ach = []
                        var maniac = this.nbAchievedManiac
                        var i
                        for(i = 0; i < totalDefi;  ++i){
                            if(i < maniac){
                                ach.push({id: i, pic: 1})
                            } else {
                                ach.push({id: i, pic: 0})
                            }
                        }
                        return ach; 
                    }) 
                }
            },

            nbCompleted: {
                get: function(){
                    return this.nbAchievedCoeur + this.nbAchievedManiac + this.nbAchievedSport;
                }
            }


        }
    }
</script>