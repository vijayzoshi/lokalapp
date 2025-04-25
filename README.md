# lokalapp

Lokalapp is a job-searching app that allows users to browse and bookmark job listings. The app has a jobs fragment that displays multiple job listings and a detailed job screen where users can view the specifics of a selected job. Bookmarked jobs can be viewed offline too without any internet connection.

## Features
- Bottom Navigation: The app provides a bottom navigation UI with two sections: "Jobs" and "Bookmarks".
- Jobs Screen: Fetches job data from the API endpoint and displays it in a list with infinite scrolling.
- Job Details: Clicking on a job card navigates to a detailed view of the job.
- Bookmark: Users can bookmark jobs, and these bookmarks are saved in a local database for offline access.
- State Management: Handles different states such as loading, error, and empty states throughout the app.

## Tech Stack
Kotlin, XML, Retrofit, Room, Coroutine, Navigation Component, Glide.

## API Endpoint 
https://testapi.getlokalapp.com/common/jobs?page=1

## Demo Video 
https://drive.google.com/file/d/1Sm5ySynqgj22ujfpK3H6d0mV1JrrTBlU/view?usp=drive_link
