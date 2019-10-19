package monolith

import java.time.Year

case class Poem(author: String,
                title: Option[String],
                poem: String,
                publicationYear: Option[Year])
