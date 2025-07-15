# 🖥️ Visual Workspace Editor (JavaFX)

An interactive editor for creating and manipulating objects in a scalable visual workspace. Built with **JavaFX** and structured using the **MVC architecture**, this application supports advanced GUI features such as **multiple views, portals, object transformations**, and **dynamic user interactions**.  

---

## 🌟 Project Overview

This editor allows users to design and manipulate a virtual workspace filled with interactive objects. It includes features like **snap-to-grid, multiple viewports, panning, resizing handles, and nested portals** that provide a window into other parts of the workspace.  

Developed as part of **CMPT 381: GUI Programming**, this project demonstrates advanced GUI programming concepts and interactive design patterns.  

- **Language**: Java  
- **Framework**: JavaFX  
- **Architecture**: MVC (Model-View-Controller)  

---

## 🚀 Features

### 🖌️ Object Creation & Manipulation
- Create new rectangular objects by **clicking and dragging** in the workspace.  
- Select and move existing objects interactively.  
- Resize objects using corner **handles**, supporting transformations in all directions.  

### 🌍 Multiple Views
- **Detail View**: The main workspace area for drawing and editing.  
- **Mini View**: A miniature overview of the entire workspace with interactive panning and zooming capabilities.  

### 🪞 Portal Objects
- **Portals** act as dynamic windows into other areas of the workspace.  
- Nested portals allow multi-level viewing of the workspace.  
- Pan and zoom inside portals for enhanced navigation.  

### ↩️ Undo & Redo (if implemented)
- Undo and redo transformations, movements, and object creation.  

---

## 🕹️ Controls

| Key/Action                     | Function                                                  |
|--------------------------------|-----------------------------------------------------------|
| **Mouse Drag**                 | Create new rectangle or move existing object              |
| **Shift + Mouse Drag**         | Pan the Detail View                                       |
| **Control + Mouse Drag**       | Create a Portal                                           |
| **Click on Object**            | Select object                                             |
| **Control + Click**            | Multi-select objects                                      |
| **Drag Handles**               | Resize object                                             |
| **Delete/Backspace**           | Delete selected object(s)                                 |
| **Up/Down Arrow**              | Zoom in/out (for selected Portal view)                    |

---

## 🛠 Tech Stack

- **Language**: Java  
- **Framework**: JavaFX  
- **Architecture**: MVC (Model-View-Controller)  
- **Design Patterns**: Publish-Subscribe, State Machines  

---

## ▶️ How to Run

1. Clone or download this repository.  
2. Open the project in **IntelliJ IDEA** (or any Java IDE).  
3. Ensure **JavaFX SDK** is installed and configured in your IDE.  
4. Navigate to: src/main/java/org/example/assignment3/EditorApp.java
5. Run `EditorApp.java` to launch the application.  

---

## 📖 Course Context

This project was developed as part of **CMPT 381: GUI Programming** at the University of Saskatchewan. It demonstrates key GUI development skills including advanced interactions, multiple views, and scalable workspace design.  

---

## 👨‍💻 Author

- **Pratham Shah** (mvr659)  
