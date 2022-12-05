cleari()
drawStage(blue)
val cb = canvasBounds
val pic = Picture.rectangle(45,45)
  pic.setPosition(cb.x + 560, cb.y + 5)
    draw(pic)

val js = joystick(20)
  js.setPosition(cb.x + cb.width / 2, cb.y + 20 + 10)
    js.draw()

animate {
  js.movePlayer(pic, 1)
}
