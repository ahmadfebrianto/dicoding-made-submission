package com.ahmadfebrianto.moviecatalogue.data

import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity


object DummyData {

    fun getMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        val listOfMovies: List<Map<String, String>> = listOf(
            mapOf(
                "movieId" to "mv1",
                "poster_path" to "file:///android_asset/poster_movie/mv1.png",
                "title" to "The Shawshank Redemption",
                "description" to "In 1947 Portland, Maine, banker Andy Dufresne is convicted of murdering his wife and her lover and is sentenced to two consecutive life sentences at the Shawshank State Penitentiary. He is befriended by Ellis \"Red\" Redding, an inmate and prison contraband smuggler serving a life sentence.",
                "rating" to "9.3",
                "release_year" to "1994",
                "stars" to "Tim Robbins, Morgan Freeman, Bob Gunton",
                "director" to "Frank Darabont"
            ),
            mapOf(
                "movieId" to "mv2",
                "poster_path" to "file:///android_asset/poster_movie/mv2.png",
                "title" to "The Godfather",
                "description" to "It is the first installment in The Godfather trilogy. The story, spanning from 1945 to 1955, chronicles the Corleone family under patriarch Vito Corleone (Brando), focusing on the transformation of his youngest son, Michael Corleone (Pacino), from reluctant family outsider to ruthless mafia boss.",
                "rating" to "9.2",
                "release_year" to "1972",
                "stars" to "Marlon Brando, Al Pacino, James Caan",
                "director" to "Francis Ford Coppola"
            ),
            mapOf(
                "movieId" to "mv3",
                "poster_path" to "file:///android_asset/poster_movie/mv3.png",
                "title" to "The Dark Knight",
                "description" to "The Dark Knight is a 2008 superhero film directed, produced, and co-written by Christopher Nolan. Based on the DC Comics character Batman, the film is the second installment of Nolan's The Dark Knight Trilogy and a sequel to 2005's Batman Begins, starring Christian Bale and supported by Michael Caine, Heath Ledger, Gary Oldman, Aaron Eckhart, Maggie Gyllenhaal, and Morgan Freeman. In the film, Bruce Wayne / Batman (Bale), Police Lieutenant James Gordon (Oldman) and District Attorney Harvey Dent (Eckhart) form an alliance to dismantle organized crime in Gotham City, but are menaced by an anarchistic mastermind known as the Joker (Ledger), who seeks to undermine Batman's influence and throw the city into anarchy.",
                "rating" to "9.0",
                "release_year" to "2008",
                "stars" to "Christian Bale, Heath Ledger, Aaron Eckhart",
                "director" to "Christopher Nolan"
            ),
            mapOf(
                "movieId" to "mv4",
                "poster_path" to "file:///android_asset/poster_movie/mv4.png",
                "title" to "12 Angry Men",
                "description" to "The film focuses on a jury's deliberations in a capital murder case. A 12-man jury is sent to begin deliberations in the first-degree murder trial of an 18-year old Puerto Rican boy accused in the stabbing death of his father, where a guilty verdict means an automatic death sentence.",
                "rating" to "9.0",
                "release_year" to "1957",
                "stars" to "Henry Fonda, Lee J. Cobb, Martin Balsam",
                "director" to "Sidney Lumet"
            ),
            mapOf(
                "movieId" to "mv5",
                "poster_path" to "file:///android_asset/poster_movie/mv5.png",
                "title" to "Schindler's List",
                "description" to "Schindler's List chronicles the trials and triumph of one man who made a difference and the tribulations of those who survived one of the darkest chapters in human history because of his actions. Businessman Oskar Schindler arrives in Krakow in 1939, ready to make his fortune from World War II, which has just started.",
                "rating" to "8.9",
                "release_year" to "1993",
                "stars" to "Liam Neeson, Ralph Fiennes, Ben Kingsley",
                "director" to "Steven Spielberg"
            ),
            mapOf(
                "movieId" to "mv6",
                "poster_path" to "file:///android_asset/poster_movie/mv6.png",
                "title" to "The Lord of the Rings",
                "description" to "The film begins with a summary of the prehistory of the ring of power. Long ago, twenty rings existed to three for elves, seven for dwarves, nine for men, and one made by the Dark Lord Sauron, in Mordor, which would rule all the others. Sauron poured all his evil and his will to dominate into this ring. An alliance of elves and humans resisted Sauron’s ring and fought against Mordor. They won the battle and the ring fell to Isildur, the son of the king of Gondor, but just as he was about to destroy the ring in Mount Doom, he changed his mind and held on to it for himself. Later he was killed, and the ring fell to the bottom of the sea. The creature Gollum discovered it and brought it to his cave. Then he lost it to the hobbit Bilbo Baggins.",
                "rating" to "8.9",
                "release_year" to "2003",
                "stars" to "Elijah Wood, Viggo Mortensen, Ian McKellen",
                "director" to "Peter Jackson"
            ),
            mapOf(
                "movieId" to "mv7",
                "poster_path" to "file:///android_asset/poster_movie/mv7.png",
                "title" to "Pulp Fiction",
                "description" to "Image result for pulp fiction description\nThe title refers to the pulp magazines and hardboiled crime novels popular during the mid-20th century, known for their graphic violence and punchy dialogue. Tarantino wrote Pulp Fiction in 1992 and 1993, incorporating scenes that Avary originally wrote for True Romance (1993).",
                "rating" to "8.9",
                "release_year" to "1994",
                "stars" to "John Travolta, Uma Thurman, Samuel L. Jackson",
                "director" to "Quentin Tarantino"
            ),
            mapOf(
                "movieId" to "mv8",
                "poster_path" to "file:///android_asset/poster_movie/mv8.png",
                "title" to "The Good, the Bad and the Ugly",
                "description" to "The film tells the story of three men who pursue, often at the expense of others, information about the location of a buried treasure of coins. The first character introduced in the movie is Tuco Benedicto Pacifico Juan Maria Ramirez (the Ugly) - called Tuco - (Eli Wallach), who has a bounty on his head for numerous crimes. Tuco has a partnership with Blondie (The Good, played by Clint Eastwood) in which the latter turns him in for the reward money which the two then split after Blondie saves Tuco from hanging at the last moment. Meanwhile, a third character called Angel Eyes (Lee Van Cleef, playing the Bad) has learned of a hidden trunk of gold owned by a Confederate soldier named Bill Carson. He sets off to find the gold.",
                "rating" to "8.8",
                "release_year" to "1966",
                "stars" to "Clint Eastwood, Eli Wallach, Lee Van Cleef",
                "director" to "Sergio Leone"
            ),
            mapOf(
                "movieId" to "mv9",
                "poster_path" to "file:///android_asset/poster_movie/mv9.png",
                "title" to "Fight Club",
                "description" to "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more. A nameless first person narrator (Edward Norton) attends support groups in attempt to subdue his emotional state and relieve his insomniac state.",
                "rating" to "8.8",
                "release_year" to "1999",
                "stars" to "Brad Pitt, Edward Norton, Meat Loaf",
                "director" to "David Fincher"
            ),
            mapOf(
                "movieId" to "mv10",
                "poster_path" to "file:///android_asset/poster_movie/mv10.png",
                "title" to "Forrest Gump",
                "description" to "Forrest Gump is a 1994 American drama film directed by Robert Zemeckis and written by Eric Roth with comedic aspects. It is based on the 1986 novel of the same name by Winston Groom and stars Tom Hanks, Robin Wright, Gary Sinise, Mykelti Williamson and Sally Field. The story depicts several decades in the life of Forrest Gump (Hanks), a slow-witted but kind-hearted man from Alabama who witnesses and unwittingly influences several defining historical events in the 20th century United States. The film differs substantially from the novel.",
                "rating" to "8.8",
                "release_year" to "1994",
                "stars" to "Tom Hanks, Robin Wright, Gary Sinise",
                "director" to "Robert Zemeckis"
            )
        )

        for (i in listOfMovies.indices) {
            val movie = MovieEntity()
            val list = listOfMovies[i]
            movie.id = list["movieId"].toString()
            movie.poster = list["poster_path"].toString()
            movie.title = list["title"].toString()
            movie.description = list["description"].toString()
            movie.rating = list["rating"].toString()
            movie.releaseYear = list["release_year"].toString()

            movies.add(movie)
        }

        return movies
    }

    fun getMovieById(movieId: String): MovieEntity {
        var result = MovieEntity()
        val movies = getMovies()
        for (item in movies) {
            if (item.id == movieId) {
                result = item
            }
        }
        return result
    }

    fun getTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        val listOfTvShows: List<Map<String, String>> = listOf(
            mapOf(
                "movieId" to "tv1",

                "poster_path" to "file:///android_asset/poster_tv_show/tv1.png",
                "title" to "Band of Brothers",
                "description" to "Band of Brothers is a dramatized account of \"Easy Company\" (part of the 2nd Battalion, 506th Parachute Infantry Regiment), assigned to the United States Army's 101st Airborne Division during World War II. Over ten episodes the series details the company's exploits during the war.",
                "rating" to "9.4",
                "release_year" to "2001",
                "stars" to "Scott Grimes, Damian Lewis, Ron Livingston",
                "director" to "David Frankel"
            ),
            mapOf(
                "movieId" to "tv2",

                "poster_path" to "file:///android_asset/poster_tv_show/tv2.png",
                "title" to "Breaking Bad",
                "description" to "Set in Albuquerque, New Mexico, between 2008 and 2010, Breaking Bad follows Walter White, a meek high school chemistry teacher who transforms into a ruthless player in the local methamphetamine drug trade, driven by a desire to financially provide for his family after being diagnosed with terminal lung cancer.",
                "rating" to "9.4",
                "release_year" to "2008",
                "stars" to "Bryan Cranston, Aaron Paul, Anna Gunn",
                "director" to "Vince Gilligan"
            ),
            mapOf(
                "movieId" to "tv3",

                "poster_path" to "file:///android_asset/poster_tv_show/tv3.png",
                "title" to "Chernobyl",
                "description" to "Chernobyl is a 2019 historical drama television miniseries that revolves around the Chernobyl disaster of 1986 and the cleanup efforts that followed. The series was created and written by Craig Mazin and directed by Johan Renck. It features an ensemble cast led by Jared Harris, Stellan Skarsgård, Emily Watson and Paul Ritter. The series was produced by HBO in the United States and Sky UK in the United Kingdom.",
                "rating" to "9.4",
                "release_year" to "2019",
                "stars" to "Jessie Buckley, Jared Harris, Stellan Skarsgård",
                "director" to "Craig Mazin"
            ),
            mapOf(
                "movieId" to "tv4",

                "poster_path" to "file:///android_asset/poster_tv_show/tv4.png",
                "title" to "The Wire",
                "description" to "The Wire is an American crime drama television series created and primarily written by author and former police reporter David Simon. The series was broadcast by the cable network HBO in the United States. The Wire premiered on June 2, 2002 and ended on March 9, 2008, comprising 60 episodes over five seasons. The idea for the show started out as a police drama loosely based on the experiences of his writing partner Ed Burns, a former homicide detective and public school teacher.",
                "rating" to "9.3",
                "release_year" to "2002",
                "stars" to "Dominic West, Lance Reddick, Sonja Sohn",
                "director" to "David Simon"
            ),
            mapOf(
                "movieId" to "tv5",

                "poster_path" to "file:///android_asset/poster_tv_show/tv5.png",
                "title" to "Cosmos: A Spacetime Odyssey",
                "description" to "Cosmos: A Spacetime Odyssey is a 2014 American science documentary television series. The show is a follow-up to the 1980 television series Cosmos: A Personal Voyage, which was presented by Carl Sagan on the Public Broadcasting Service and is considered a milestone for scientific documentaries. This series was developed to bring back the foundation of science to network television at the height of other scientific-based television series and films. The show is presented by astrophysicist Neil deGrasse Tyson, who, as a young high school student, was inspired by Sagan. Among the executive producers are Seth MacFarlane, whose financial investment was instrumental in bringing the show to broadcast television, and Ann Druyan, a co-author and co-creator of the original television series and Sagan's wife. The show is produced by Brannon Braga, and Alan Silvestri composed the backing score.",
                "rating" to "9.3",
                "release_year" to "2014",
                "stars" to "Neil deGrasse Tyson, Stoney Emshwiller, Piotr Michael",
                "director" to "Brannon Braga"
            ),
            mapOf(
                "movieId" to "tv6",

                "poster_path" to "file:///android_asset/poster_tv_show/tv6.png",
                "title" to "Avatar: The Last Airbender ",
                "description" to "Avatar: The Last Airbender, known as Avatar: The Legend of Aang in some regions, is an American animated television series produced by Nickelodeon Animation Studios. It was co-created by Michael Dante DiMartino and Bryan Konietzko, with Aaron Ehasz as head writer. It aired on Nickelodeon for three seasons, from February 2005 to July 2008. Avatar is set in an Asiatic-like world in which some people can manipulate one of the four elements—water, earth, fire, or air—with telekinetic variants of the Chinese martial arts known as \"bending\". The only individual who can bend all four elements, the \"Avatar\", is responsible for maintaining harmony between the world's four nations, and serves as the bridge between the spirit world and the physical world. The show is presented in a style that combines anime with American cartoons, and relies on the imagery of mainly East Asian culture, with some South Asian, New World, and Inuit and Sireniki influences.",
                "rating" to "9.3",
                "release_year" to "2005",
                "stars" to "Dee Bradley Baker, Zach Tyler, Mae Whitman",
                "director" to "Michael Dante DiMartino, Bryan Konietzko"
            ),
            mapOf(
                "movieId" to "tv7",

                "poster_path" to "file:///android_asset/poster_tv_show/tv7.png",
                "title" to "Game of Thrones",
                "description" to "Game of Thrones is an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO. It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin, the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland, Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019, with 73 episodes broadcast over eight seasons.",
                "rating" to "9.3",
                "release_year" to "2011",
                "stars" to "Emilia Clarke, Peter Dinklage, Kit Harington ",
                "director" to "David Benioff, D.B. Weiss"
            ),
            mapOf(
                "movieId" to "tv8",

                "poster_path" to "file:///android_asset/poster_tv_show/tv8.png",
                "title" to "The Sopranos",
                "description" to "The Sopranos is an American crime drama television series created by David Chase. The story revolves around Tony Soprano (James Gandolfini), a New Jersey-based Italian-American mobster, portraying the difficulties that he faces as he tries to balance his family life with his role as the leader of a criminal organization. These are explored during his therapy sessions with psychiatrist Jennifer Melfi (Lorraine Bracco). The series features Tony's family members, mafia colleagues, and rivals in prominent roles—most notably his wife Carmela (Edie Falco) and his protégé/distant cousin Christopher Moltisanti (Michael Imperioli).",
                "rating" to "9.2",
                "release_year" to "1999",
                "stars" to "James Gandolfini, Lorraine Bracco, Edie Falco",
                "director" to "David Chase"
            ),
            mapOf(
                "movieId" to "tv9",

                "poster_path" to "file:///android_asset/poster_tv_show/tv9.png",
                "title" to "Rick and Morty",
                "description" to "Rick and Morty is an American adult animated science fiction sitcom created by Justin Roiland and Dan Harmon for Cartoon Network's nighttime Adult Swim programming block. The series follows the misadventures of cynical mad scientist Rick Sanchez and his good-hearted but fretful grandson Morty Smith, who split their time between domestic life and interdimensional adventures.",
                "rating" to "9.2",
                "release_year" to "2013",
                "stars" to "Justin Roiland, Chris Parnell, Spencer Grammer",
                "director" to "Dan Harmon, Justin Roiland"
            ),
            mapOf(
                "movieId" to "tv10",
                "poster_path" to "file:///android_asset/poster_tv_show/tv10.png",
                "title" to "Fullmetal Alchemist: Brotherhood",
                "description" to "Fullmetal Alchemist: Brotherhood is a Japanese anime television series adapted from the Fullmetal Alchemist manga by Hiromu Arakawa. Produced by Bones, the series is directed by Yasuhiro Irie, written by Hiroshi Ōnogi and composed by Akira Senju. Fullmetal Alchemist: Brotherhood is the second anime television series based on Fullmetal Alchemist, the first being 2003's Fullmetal Alchemist. Unlike the previous adaptation, Brotherhood is a faithful adaptation directly following the original events of the manga. The series ran for 64 episodes on MBS–TBS from April 2009 to July 2010. ",
                "rating" to "9.1",
                "release_year" to "2009",
                "stars" to "Kent Williams, Iemasa Kayumi, Vic Mignogna",
                "director" to "Hiromu Arakawa"
            )
        )

        for (i in listOfTvShows.indices) {
            val tvShow = TvShowEntity()
            val list = listOfTvShows[i]
            tvShow.id = list["movieId"].toString()
            tvShow.poster = list["poster_path"].toString()
            tvShow.title = list["title"].toString()
            tvShow.description = list["description"].toString()
            tvShow.rating = list["rating"].toString()
            tvShow.releaseYear = list["release_year"].toString()

            tvShows.add(tvShow)
        }

        return tvShows
    }

    fun getTvShowById(tvShowId: String): TvShowEntity {
        var result = TvShowEntity()
        val tvShows = getTvShows()
        for (item in tvShows) {
            if (item.id == tvShowId) {
                result = item
            }
        }
        return result
    }

}



