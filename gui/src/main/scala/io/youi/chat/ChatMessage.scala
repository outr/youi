package io.youi.chat

import io.youi.image.Image

case class ChatMessage(
  text: String,
  role: String = "user",
  image: Option[Image] = None,
  timestamp: Long = System.currentTimeMillis()
)
