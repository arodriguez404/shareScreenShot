import React from 'react';
import {Button, Text, View} from 'react-native';
import MyModule from 'MyModule';

export default function App() {
  const handleScreenshot = async () => {
    if (!MyModule) {
      throw new Error('MyModule is null.');
    }
    MyModule.screenShot()
      .then(result => {
        console.log(result);
      })
      .catch(error => {
        console.error(error);
      });
  };

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: '#fff',
      }}>
      <Text>Ejemplo de screenshot</Text>
      <Button onPress={handleScreenshot} title={'screen'} />
    </View>
  );
}
