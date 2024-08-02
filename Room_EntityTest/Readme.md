# Room Entity Test - Note-Taking Application

## Overview

The Room Entity Test is a note-taking application developed for Android using Kotlin. This application utilizes the Room Persistence Library for managing and persisting data in a local SQLite database. The app allows users to create, update, and delete notes, demonstrating basic CRUD (Create, Read, Update, Delete) operations with Room.

## Features

- **Create Notes**: Add new notes with a title and content.
- **Update Notes**: Edit existing notes.
- **Delete Notes**: Remove notes from the list.
- **View Notes**: Display a list of all notes with their titles and contents.

## Architecture

- **Room Persistence Library**: For managing local database and CRUD operations.
- **ViewModel**: Handles data for the UI and survives configuration changes.
- **LiveData**: Provides data updates to the UI.
- **RecyclerView**: Displays the list of notes.

## Project Structure

- **`MainActivity`**: The main activity that displays the list of notes and handles user interactions such as adding and updating notes.
- **`Notes_Adapter`**: Adapter class for managing the RecyclerView and handling item clicks.
- **`NotesViewModel`**: ViewModel for interacting with the repository and managing the UI data.
- **`Repo`**: Repository class that provides data operations to the ViewModel.
- **`NoteDao`**: Data Access Object (DAO) for performing database operations.
- **`Note`**: Data entity representing a note in the database.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Kingcoder44/Android-Developement.git
   cd Android-Developement/Room_EntityTest
   ```

2. **Open in Android Studio**:
   Open Android Studio and select "Open an existing project," then navigate to the `Room_EntityTest` directory.

3. **Build and Run**:
   Build and run the project on an emulator or physical device.

## Dependencies

- AndroidX
- Room Persistence Library
- Kotlin Coroutines
- RecyclerView
- LiveData

## Contributing

Feel free to open issues or submit pull requests. Contributions to improve the application are welcome!

## Contact

For any questions or feedback, please contact [skushagra645@gmail.com](mailto:skushagra645@gmail.com).
