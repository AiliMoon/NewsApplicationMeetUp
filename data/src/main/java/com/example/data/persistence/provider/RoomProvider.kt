package com.example.data.persistence.provider

import com.example.data.persistence.ApplicationDatabase

interface RoomProvider {
    fun provide(): ApplicationDatabase
}
