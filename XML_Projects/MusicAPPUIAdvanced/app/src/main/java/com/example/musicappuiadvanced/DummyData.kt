package com.example.musicappuiadvanced

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon :Int, val name : String)

val lib_item = listOf<Lib>(
    Lib(R.drawable.playlist_music,"Playlist"),
    Lib(R.drawable.ic_artist,"Artist"),
    Lib(R.drawable.disc,"Album"),
    Lib(R.drawable.round_music_note_24,"Songs"),
    Lib(R.drawable.ic_music_list,"Genre")
)
