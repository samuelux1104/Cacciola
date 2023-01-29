cleari()
drawStage(red)
val cb = canvasBounds

val giocatore = Picture.rectangle(20, 30)
giocatore.setFillColor(white)
giocatore.setPenColor(black)
giocatore.setPosition(cb.x + cb.width / 2, cb.y + cb.width / 3)
draw(giocatore)

val nh = 10
val cacciatori = ArrayBuffer.empty[Picture]
val velcacciatori = HashMap.empty[Picture, Vector2D]
repeatFor(1 to nh) {n =>
  val pic = Picture.rectangle(30, 30)
  pic.setFillColor(green)
  pic.setPenColor(black)
  pic.setPosition(cb.x + cb.width / 2 , cb.y + 79 + randomDouble(300, cb.height- 100))
  cacciatori.append(pic)
  val hv= Vector2D(3,1)
  velcacciatori(pic) = hv
  draw(pic)
}

def GameLost() {
  stopAnimation()
  drawCenteredMessage("hai perso", black, 35)
}

val js = joystick(20)
js.setPosition(cb.x + cb.width / 2, cb.y + 20 + 10)
js.draw()

val speed = 5

animate{
  repeatFor(cacciatori) { h =>
  var hv = velcacciatori(h)
  h.translate(hv)
      if(h.collidesWith(stageBorder)){
        hv = bouncePicOffStage(h, hv)
        velcacciatori(h) = hv
      }
      if(h.collidesWith(giocatore)) {
      GameLost()
      }
       if(h.collidesWith(giocatore)){
        GameLost()
      }
//      if(h.collidesWith(hv)) {
//        hv = bouncePicOffStage(h, hv)
//        velcacciatori(h) = hv
//      }
    }
 js.movePlayer(giocatore, 1)
}
showGameTime(100,"hai vinto", black,25)
activateCanvas()
