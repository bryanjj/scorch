/**
 * Created by bjacobs on 8/19/15.
 */
object HelloWorld {

  import breeze.linalg._

  def main(args: Array[String]) {

    // choose a dimension
    val N = 5

    // create a random NxN matrix
    val A = DenseMatrix.rand(N,N)

    // make it symmetric positive
    val Asym = A * A.t

    // make it definite
    val Adef = Asym + DenseMatrix.eye[Double](N) * .001

    val b = DenseVector.rand(N)

    val J = (x: DenseVector[Double]) => 0.5 * x.dot(A * x) - b.dot(x)

    val dJ = (x: DenseVector[Double]) => (A * x) - b


    print(J(DenseVector.rand(N)))

    val Ainv = inv(A)

    println(Ainv)

    val xs = inv(A) * b
    print(s"J(x^*) = ${J(xs)}")

    val guess:DenseVector[Double] = DenseVector.rand(N)

    val Lr = 0.001
    val i = Iterator.iterate(guess)(x => x - dJ(x) * Lr)


    val r = (0 to 2000).map(x => (x, J(i.next())))

    r.takeRight(100).foreach(println)



//      x = x - dJ(x)*lr
//    -- we print the value of the objective function at each iteration
//      print(string.format('at iter %d J(x) = %f', i, J(x)))

//
//    //add a linear term
//    val b = torch.rand(N)
//
//    -- create the quadratic form
//    function J(x)
//    return 0.5*x:dot(A*x)-b:dot(x)
//
//    end

  }

}
