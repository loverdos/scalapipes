### What
Scala with forward pipe operator, as in F# and OCaml.

The original use-case is how to handle collections, that is why the parent package of all the code is `com.ckkloverdos.collection.pipes`.

To get an idea of what you can do:

    import com.ckkloverdos.pipes.collection.pipes._
    
    val letters = Seq('a', 'b', 'c', 'd', 'a')
    val As = letters |> 
             PSeq.filter(ch => ch == 'a')
    // _ works too:
    val As_ = letters |>
              PSeq.filter(_ == 'a')
    
    val numbers = IndexedSeq(1, 2, 3, 4, 5, 6)
    val lessThanFour = numbers |>
                       PSeq.filter(_ < 4)
                       
    val keys = Map(1 -> "keyA", 2 -> "keyB", 3 -> "keyC")
    val keysPlus = keys |>
        PMap.map { case (k, v) => (k + 1, v) } |>
        PMap.ofIterable
    
Note that due to Scala's syntax quirkiness, `|>` has to be at the end of the line, so that this won't work the way you would write it in `F#`:

    // !! Does not compile
    val lessThanFour = numbers
                       |> PSeq.filter(_ < 4)
                       


### Why
First, I did it for fun, while learning `F#` and it was a small design challenge to translate it into Scala. The pipe operator is mainstream now.  It *has* been since Unix time. `Ocaml` will have it builtin in its next version (I did not check, this could be live now).

I discovered the code to be more readable this way than the traditional `OO` dot chaining. Why? Because the flow is still natural (you start from the original data and you keep transforming them) and the intent is crystal clear: at each step you can actually understand both the flow of data and the flow of their respective types. I have not seen this noted elsewhere. This observation struck me as lightning and I think is of paramount importance for code readability.

Currently, I am using this at a couple of projects, trying to see how it scales.

### Details
This is the pipe operator in `OCaml` and `F#`:

    let (|>) x f = f x

and this is the translation I gave it for `Scala`:

    implicit final class MLPipe[T](val x: T) extends AnyVal {
        def |>[B](f: (T) => B) = f(x)
    }

So, any value is lifted to an object having the pipe operator built-in.

The implementation is deliberately kept *simple*, for now at least.

Type inference as served from the compiler may not be your best friend at all times.

Also, I could do away with some code duplication, although this does not bother me very much right now.

Overall, the fun factor of doing this has been enormous.

### Motto
> Less is more. Use both with a pipe.
