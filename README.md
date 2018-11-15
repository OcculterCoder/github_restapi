This is a RESTful API with one endpoint which has the ability to provide a list of Github's trending repositories based on their 
number of stars and please be noted that these repositories were created within last 7 days. This API sums up each repository's html url with
its corresponding no of stars and watchers.

The project is built with Maven and Jersey framework.

1. GET /repositories : returns a list of repositories following the already mentioned criteria.

2. The s search term enables to search any combination of the supported repository search qualifiers by Github. 
For example: GET /repositories?s=hello enables to find out trending hello repositories following the already mentioned criteria.

3. The start and size parameter in the request helps to limit the repositories. 
For example: GET /repositories?start=3&size=6 gives a list of top repositories from 3 to 8; total 6 repositories.
