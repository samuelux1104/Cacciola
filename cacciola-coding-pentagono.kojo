def sum(n1: Int, n2: Int) = {
    n1 + n2
}

def quadrato(size: Int) = {
    repeat(4){
      forward(size)
      left(90)
    }
}
def pentagono(size: Int) = {
    repeat(5) {
      forward(size)
      right(360/5)
    }
}
 clear()
 val xx = sum(30, 40)
 quadrato(xx)
 hop(xx*2)
 pentagono(xx)