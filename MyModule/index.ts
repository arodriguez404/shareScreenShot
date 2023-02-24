import { NativeModules } from 'react-native';

type MyModuleType = {
    screenShot(): Promise<void>;
  };
  

const MyModule: MyModuleType = NativeModules.MyModule;

export default MyModule;
