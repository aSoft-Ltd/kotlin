# Changelog
All notable changes to this project will be documented in this file.

## 0.0.16 -
### Deployment
#### Added
- Every kotlin jvm target is an output with a main class attribute

### Firebase
#### Fixed
- CollectionReference.put() for android

## 0.0.15 - 2020/05/22
### Storage
#### Changed
- Common Class Storage to interface IStorage
#### Added
- Individual Storage contractors in each target platform
#### Removed
- Dependency to platform library

### Firebase
#### Updated
- Firebase JS version from 5.11 to 7.14.1
- Using memory firestore instead of indexdB firestore
#### Fixes
- Fixed CollectionReference.doc() requires a parameter

### UI
#### Updates
- Bumped kotlin-js versions to pre 105
- Bumped npm versions
  ```
    "react": "16.13.1",
    "react-dom": "16.13.1",
    "styled-components": "5.1.0",
    "inline-style-prefixer": "6.0.0",
    "react-router-dom": "5.1.2",
    "text-encoding": "0.7.0",
    "core-js": "3.0.0",
    "react-responsive-carousel": "3.1.47",
    "react-event-timeline": "1.5.4",
    "echarts": "4.2.1",
    "simplebar-react": "2.0.10",
    "echarts-for-react": "2.0.14",
    "react-tabs": "3.1.0",
    "react-table": "6.10.3",
    "react-draft-wysiwyg": "1.13.2",
    "draft-js": "0.10.5",
    "draft-js-export-html": "1.4.1",
    "react-icons": "3.10.0",
    "@react-google-maps/api": "1.3.0"
  ```

## 0.0.14 - 2020/05/14
### Frontend
#### Added
- Create Tasks and Namespaces for each suported kotlin mpp target
#### Fixed
- deploy{Debug|Staging|Release} picking up old js bundle

## 0.0.10 - 2020/05/12
### UI
#### Added
- Show lambda has been marked as a suspend lambda

#### Fixed
- AdminUserAccounts being created over and over again

### Persist
- Changed all function parameters to accept Collection instead off list

