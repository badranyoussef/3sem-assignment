import React from 'react'
import ReactDOM from 'react-dom/client'

{/* Således importerer vi enten eller flere komponenter
Importeres én komponen kan den navngives det man ønsker.
Importeres flere, så skal de navngives med deres opgivet navn i JSX filen */}
import App from './App.jsx'
import PersonViewer from './components/PersonViewer.jsx'

import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
