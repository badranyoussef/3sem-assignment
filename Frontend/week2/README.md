# Assignment Readme Guidelines

## Overview

- Youssef Badran
- all assignment are included.


## 2. Please reflect on the following questions
2.1 What is the purpose of the key value, which must be given to individual rows in a react-list?
The key value uniquely identifies each element in a list of React components. It helps React identify which items have changed, are added, or are removed. Without a unique key, React may have difficulty efficiently updating the UI when the list changes.

2.2 It's recommended to use a unique value from your data if available (like an ID). How do you get a unique value in a map callback, for data without a unique id?
If your data doesn't have a unique identifier, you can use the index of the array as a fallback. However, it's generally better to have a unique identifier associated with each item in your data.

2.3 What is the difference(s) between state and props?
State: State is internal to a component and can be changed over time through user interactions or other events. It is managed and controlled by the component itself using functions like useState or useReducer.
Props: Props (properties) are data passed from a parent component to a child component. They are immutable and are used to customize or configure a component. Props are fixed throughout the component's lifecycle and cannot be changed by the component itself.


2.4 For which scenarios would you use props, and for which would you use state?
Props: Props are used to pass data from parent components to child components. They are ideal for configuring or customizing child components based on parent data. Props are immutable and should not be changed by the child component.
State: State is used to manage component-specific data that can change over time. It is useful for handling user input, managing UI state, or storing data fetched from an API. State is internal to the component and can be changed using setState or similar functions.


## Friday presentation
- add the friday presentation into the weekly folder
