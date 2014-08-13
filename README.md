word-hoard
==========

Word-Hoard is a rambly project to investigate letter and word frequencies in
text, and see if there's anything to say about that.  It can be used for
questions like: 

* Are the fake Barack Obama streams on Twitter good imitations of the real
  one, in terms of letter, punctuation, or word usage?

* How does the abbreviated language people use on Twitter compare to the
  less-abbreviated language the same people use on Facebook?

* How did the use of letters and punctuation change in English over the last
  few centuries?

It is very much a work in progress: I'm still poking around for good ways to
ask questions and collect data. It's also an exercise in using the new
features Java 1.7 and 1.8: the last time I did Java was 1.6.

-------

We start with 'Fragments', which are the fundamental bits we work with:
characters if we're thinking about letters and punctuation, words if we're
thinking about words. We might get Letter or Punctuation fragments if we want
to look at kinds of characters. This is basically a veneer around
java.lang.String, and I'm not entirely sure it's worth the bother of having it
as a separate type.

A 'Corpus' is a body of text that we're going to analyze. (Plural: 'corpora')
I've currently got 'StringCorpus' (mostly for testing) and 'FileCorpus'
(information stored in a file). I'm planning to have things like
'TwitterCorpus', which will read a chunk of a Twitter feed the first time it's
used and cache it in a file after that.

A 'Dissector' is a way to slice up a Corpus into Fragments.  There will be one
Dissector type for each Fragment type.

A 'Dissection' is a Corpus that has been sliced into Fragments. 

An 'Analysis' is an ad-hoc tool that takes a Dissection and thinks about it.
We've got Histogram, which counts how often each Fragment appears.  There's
GraphHistogram, which produces a HTML document with a SVG graph of the
histograms of several corpora.   And there's ChiSquared, which does a
statistical test to see if the corpora have the same distribution of
fragments.

(And, as of 8/13/2014, the answer is "They don't have the same distribution".
Alice in Wonderland and Alice Through the Looking-Glass have quite different
distributions by the chi-squared test. This is clearly the wrong answer — or
at least the right answer to the wrong question. I'd like to detect that
they're by the same author. More work!)
