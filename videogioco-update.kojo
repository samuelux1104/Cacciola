cleari()
drawStage(black)
print("benvenuto, in questo gioco il tuo obbiettivo sarà quello di arrivare a 100 punti prima di perdere tutte le tue 4 vite, le palline rosse ti toglieranno 1 vita quelle blu ti toglieranno 5 punti e quelle gialle ti aggiungeranno 20 punti")
val cb = canvasBounds
var nVite = 4
var cPos = 0
val contVit = ArrayBuffer.empty[Picture]

// qui mettiamo vite e punteggio  
  var vite = HashSet.empty[Picture]
  repeat(nVite){
    val vit = puntivita
    vit.setPosition(cb.x + cb.width - 15  - cPos*25, cb.y + cb.height - 10)
    cPos+=1
    vit.draw()
    contVit.append(vit)
  }
//le vite
def puntivita = Picture{
  setFillColor(green)
  setPenColor(yellow)
    repeat(4){
      right(90)
      forward(10)
  }
}
//come vengono tolte le vite
def togliVita {
  cPos -=1 
  contVit(cPos).erase()
  if(cPos == 0) {
    stopAnimation()
    draw(lose)
  }
}
//come fare la scritta per la vittoria e per la sconfitta
val win = Picture text("hai vinto",40)
win.setPenColor(purple)
val lose = Picture.text("hai perso", 40)
lose.setPenColor(orange)
//come fare il punteggio
var punteggio = 0
timer(2000){
  punteggio = punteggio + 1
  nPunti.update(punteggio)
}
if(punteggio == 100){
  stopAnimation()
  draw(win)
}

val nPunti = Picture.textu(punteggio,30)
    nPunti.translate(cb.x + cb.width - 40, cb.y + cb.height - 350)
    nPunti.setPenColor(green)
    draw(nPunti)
//creiamo delle altre palline che toglieranno punteggio al player
def pallaBlu = {
  fillColor(blu) -> Picture.circle(5)
  }
//implementiamole in gioco 
val cannoneBlu =HashSet.empty[Picture]
timer(3500){
  val k = pallaBlu
  k.setPosition(cb.x + 300, cb.y + 400)
  k.setHeading(-random(1 , 180))
  cannoneBlu.add(k)
  draw(k)    
}
//creiamo delle palline che daaranno del punteggio in più al player
def pallaGialla = {
  fillColor(yellow) -> Picture.circle(7)
}
//implementiamole 
val cannoneGiallo = HashSet.empty[Picture]
timer(15000){
  val z = pallaGialla
  z.setPosition(cb.x + 300, cb.y + 20)
  z.setHeading(random(1,180))
  cannoneGiallo.add(z)
  draw(z)
}
//iniziamo a creare i cannoni partendo dallda DEF
def nuovaPalla = {
    fillColor(red) -> Picture.circle(5)
}
//ora creiamo i cannoni in ogni angolo
val ostacoli = HashSet.empty[Picture]
//cannone"b" nel primo angolo
timer(1000) {
    val b = nuovaPalla
    b.setPosition(cb.x + 5, cb.y + 5)
    b.setHeading(-random(70))
    ostacoli.add(b)
    draw(b)
}
//cannone"c" nel secondo angolo
timer(1000) {
    val c = nuovaPalla
    c.setPosition(cb.x + 570, cb.y + 5)
    c.setHeading(random(40, 180))
    ostacoli.add(c)
    draw(c)  
}
//cannone "d" nel terzo angolo
timer(1000) {
    val d = nuovaPalla
    d.setPosition( cb.x + 5, cb.y + 400)
    d.setHeading(random(180, 300))
    ostacoli.add(d)
    draw(d)
}
//cannone "e" nel quarto angolo ovvero l' ultimo
timer(1000) {
    val e = nuovaPalla
    e.setPosition( cb.x + 540 , cb.y + 390)
    e.setHeading(random(40,180))
    ostacoli.add(e)
    draw(e) 
    }    
//qui mettiamo il player
val player = Picture{
  setPenColor(gray)
  setPenThickness(2)
  setFillColor(blue)
   forward(10)
  repeat(4){
    forward(12)
    right(90)
  }
  right(90)
  hop(12)
  right(90)
  forward(12)
  right(180)
  hop(18)
   right(90)
   forward(10)
   right(180)
   hop(22)
   forward(12)
   left(180)
   forward(12)
   left(90)
   hop(14)
   right(360,6)
}  
draw(player)
// alcni comandi per far muovere le palline
var vel = Vector2D(0.5 , 0.5)
var gioco = true
val speed = 5
animate {
//qui ci sono gli animate del player
   if (isKeyPressed(Kc.VK_RIGHT)) {
        player.translate(speed, 1)
    }
    if (isKeyPressed(Kc.VK_LEFT)) {
        player.translate(-speed, 1)
    }
    if (isKeyPressed(Kc.VK_UP)) {
        player.translate(1, speed)
    }
    if (isKeyPressed(Kc.VK_DOWN)) {
        player.translate(1, -speed)
    }
//iniziamo con gli animate delle palline
//1°b
    ostacoli.foreach { b =>
        if(gioco){
        b.translate(vel)
        }
    }
//1°c    
    ostacoli.foreach { c =>
        if(gioco){
        c.translate(vel)
        }
    }
//1°d
    ostacoli.foreach { d =>
        if(gioco){
        d.translate(vel)
        }
    }
//1°e
    ostacoli.foreach { e =>
      if(gioco){
        e.translate(vel)
      }
    }
//1°k
    cannoneBlu.foreach { k =>
      if(gioco){
        k.translate(vel)
      }
    }
//1°z
   cannoneGiallo.foreach { z =>
      if(gioco){
        z.translate(vel)
      }
   }
//secondo animate palline
//2°b
    ostacoli.foreach { b =>
        if (b.collidesWith(stageBorder)) {
            ostacoli.remove(b)
            b.erase()
        }
    }
//2°c
    ostacoli.foreach { c =>
        if (c.collidesWith(stageBorder)){
            ostacoli.remove(c)
            c.erase()
        }
    }
//2°d    
    ostacoli.foreach { d =>
        if (d.collidesWith(stageBorder)){
          ostacoli.remove(d)
          d.erase()
        }
    }
//2°e
    ostacoli.foreach { e =>
      if(e.collidesWith(stageBorder)){
        ostacoli.remove(e)
        e.erase()
      }
  }
//2°k
    cannoneBlu.foreach { k =>
      if(k.collidesWith(stageBorder)){
        cannoneBlu.remove(k)
        k.erase()
      }
    }
//2°z
    cannoneGiallo.foreach { z =>
      if(z.collidesWith(stageBorder)){
        cannoneGiallo.remove(z)
        z.erase()
      }
    }
//ora implichiamo la perdita di vite
ostacoli.foreach { b =>
      if(b.collidesWith(player)) {
        ostacoli.remove(b)
        b.erase
        togliVita
      }
    }
    ostacoli.foreach { c =>
      if(c.collidesWith(player)) {
        ostacoli.remove(c)
        c.erase
        togliVita
      }
    }
    ostacoli.foreach { d =>
      if(d.collidesWith(player)) {
        ostacoli.remove(d)
        d.erase
        togliVita
      }
    }
    ostacoli.foreach { e =>
      if(e.collidesWith(player)) {
        ostacoli.remove(e)
        e.erase
        togliVita
      }
    }  
//implementiamo la perdita di punteggio
   cannoneBlu.foreach { k =>
    if(k.collidesWith(player)){
      cannoneBlu.remove(k)
      k.erase
      punteggio = punteggio - 5
      nPunti.update(punteggio)
    }
  }
//implichiamo l aggiunta di punteggio 
  cannoneGiallo.foreach { z =>
    if(z.collidesWith(player)){
      cannoneGiallo.remove(z)
      z.erase
      punteggio = punteggio + 20
      nPunti.update(punteggio)
    }
  }
}

