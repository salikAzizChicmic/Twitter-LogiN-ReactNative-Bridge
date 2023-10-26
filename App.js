import React, { useEffect } from 'react'
import { Text,NativeModules } from 'react-native';



const App = () => {
    useEffect(()=>{
        var OpenActivity = NativeModules.OpenActivity;
        OpenActivity.open()
    })
  return (
    <Text>Hello</Text>
  )
}

export default App