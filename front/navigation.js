// Navigation/Navigation.js

import React from 'react' // N'oubliez pas l'import de React ici. On en a besoin pour rendre nos components React Native Image ! 
import { StyleSheet, Image } from 'react-native';
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
        Covid19 : {
            screen : CoronaScreen,
            navigationOptions: {
                tabBarIcon: () => {
                return <Image
                    source={require('./assets/images/navigation/coronascreen.png')}
                    style={styles.icon}/>
                }
            }
        },
        Categories: {
            screen : CategoryTab,
            navigationOptions: {
                tabBarIcon: () => {
                return <Image
                    source={require('./assets/images/navigation/categoryscreen.png')}
                    style={styles.icon}/>
                }
            }
        },
        Défis: {
            screen : ChallengesTab,
            navigationOptions: {
                tabBarIcon: () => {
                return <Image
                    source={require('./assets/images/navigation/challengescreen.png')}
                    style={styles.icon}/>
                }
            }
        },
        Profile: {
            screen : ProfileScreen,
            navigationOptions: {
                tabBarIcon: () => {
                return <Image
                    source={require('./assets/images/navigation/profilescreen.png')}
                    style={styles.icon}/>
                }
            }
        },
        Details: {
            screen : DetailsScreen,
            navigationOptions: {
                tabBarIcon: ({tintColor}) => {
                    return <Image
                    source={require('./assets/images/navigation/detailscreen.png')}
                    style={styles.icon}/>
                }
            }
        }
    },
    {
        tabBarOptions: {
        activeBackgroundColor: '#DDDDDD', // Couleur d'arrière-plan de l'onglet sélectionné
        inactiveBackgroundColor: '#FFFFFF', // Couleur d'arrière-plan des onglets non sélectionnés
        showLabel: false, // On masque les titres
        showIcon: true,
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