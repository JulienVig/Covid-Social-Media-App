// Navigation/Navigation.js

import React from 'react' // N'oubliez pas l'import de React ici. On en a besoin pour rendre nos components React Native Image ! 
import { StyleSheet, Image, Easing, Animated } from 'react-native';
import { Ionicons, FontAwesome, MaterialCommunityIcons, AntDesign } from '@expo/vector-icons';
import { createStackNavigator, createAppContainer, createBottomTabNavigator } from 'react-navigation'
import LoginScreen from "./components/Screens/LoginScreen";
import CoronaScreen from "./components/Screens/CoronaScreen";
import DetailsScreen from "./components/Screens/DetailsScreen";
import ProfileScreen from "./components/Screens/ProfileScreen";
import ChallengesScreen from "./components/Screens/ChallengesScreen";
import ChallengeDetailScreen from "./components/Screens/ChallengeDetailScreen";
import CategoryScreen from "./components/Screens/CategoryScreen";
import ValidationChallengeScreen from "./components/Screens/ValidationChallengeScreen";
import ChallengesByCategoryScreen from "./components/Screens/ChallengesByCategoryScreen";

const CategoryTab = createStackNavigator(
    {
        Category: CategoryScreen,
        ChallengesByCategory : ChallengesByCategoryScreen,
        ChallengeDetail : ChallengeDetailScreen,
        Validation : ValidationChallengeScreen,
    },
)

const ChallengesTab = createStackNavigator(
    {
        ChallengesScreen: ChallengesScreen,
        ChallengeDetail : ChallengeDetailScreen,
        Validation : ValidationChallengeScreen,
    },
)

const Tabs = createBottomTabNavigator(
    {
        "Covid-19" : {
            screen : CoronaScreen,
            navigationOptions: {
                tabBarIcon: ({ focused}) => {
                  let iconName = 'warning';
                  let color = focused ? '#980740' : 'gray';
                  return <AntDesign name={iconName} size={28} color={color} />;
                },
            }
        },
        "Catégories": {
            screen : CategoryTab,
            navigationOptions: {
              tabBarIcon: ({ focused}) => {
                let iconName = focused ? 'hexagon-slice-6': 'hexagon-outline';
                let color = focused ? '#2c3c74' : 'gray';
                return <MaterialCommunityIcons name={iconName} size={30} color={color}/>;
              },
            }
        },
        "Défis": {
            screen : ChallengesTab,
            navigationOptions: {
              tabBarIcon: ({ focused}) => {
                let iconName = focused ? 'home': 'home-outline';
                let color = focused ? '#3d9d84' : 'gray';
                return <MaterialCommunityIcons name={iconName} size={30} color={color}/>;
              },
            }
        },
        "Profil": {
            screen : ProfileScreen,
            navigationOptions: {
              tabBarIcon: ({ focused}) => {
                let iconName = focused ? 'user': 'user-o';
                let color = focused ? 'orange' : 'gray';
                return <FontAwesome name={iconName} size={28} color={color}/>;
              },
            }
        },
        "Détails": {
            screen : DetailsScreen,
            navigationOptions: {
              tabBarIcon: ({ focused}) => {
                let iconName = focused ? 'ios-information-circle': 
                'ios-information-circle-outline';
                let color = focused ? '#fedff2' : 'gray';
                return <Ionicons name={iconName} size={30} color={color}/>;
              },
            }
        }
    },
    {
        tabBarOptions: {
            // activeBackgroundColor: '#DDDDDD', // Couleur d'arrière-plan de l'onglet sélectionné
            inactiveBackgroundColor: '#FFFFFF', // Couleur d'arrière-plan des onglets non sélectionnés
            showLabel: true, // On masque les titres
            showIcon: true,
            activeTintColor: "black",
        }
    }
);

const StackNavigator = createStackNavigator(
    {
        Home: LoginScreen,
        Tabs
    },
    {
        headerMode: 'none'
    }
  );


const styles = StyleSheet.create({
  icon: {
    width: 30,
    height: 30
  }
})

 

export default createAppContainer(StackNavigator)