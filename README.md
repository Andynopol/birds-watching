# Birds Wathcing

Birds watching is mock application that can CReate, Update and Delete Birds and Sightings entities. Birds can be sighted multiple times in different location and different times.

## Starup

Create the docker image
```bash
docker build ./ -t birds-watching
```

Run the project
```bash
docker compose -f ./docker-compose.yml up -d
```

## Test the API

Using Postman, import the `birds-watching.postman_collection.json` and you can play with the API.

## Entities

### Bird

A bird entity has a `name`, a `color`, a `weight` and a `height`. Also, a bird has a list of sightings as a relation(one-to-many).

```json
{
    "name": "Canary",
    "color": "yellow",
    "weight": 8,
    "height": 10,
    "sightings": []
}
```

### Sightings

A sigting entity has a `location` and a `timestamp`. They have a many-to-one relation with a bird entity

```json
{
    "location": "Bucegi",
    "timestamp":"2024-08-11T21:18:05.418296",
    "bird": <Bird>
}
```

## Endpoints

### Birds

* `GET /birds` retuns a list of all birds.
* `GET /birds?name=${NAME}` returns a list of birds based on the name.
* `GET /birds?color=${COLOR}` returns a list of birds based on their color
* `GET /birds?name=${NAME}&${COLOR}` returns a list of birds based on their name and color
* `POST /birds` creates a bird with the provided payload
* `PATCH /birds/${ID}` updates a bird if the id provided is valid
* `DELETE /birds/${ID}` deletes a bird if the id provided is valid

### Sightings

* `GET /sightings/${birdId}` returns a list of sightings of a specific bird
* `POST /sightings/location` returns a list of sightings in a specific location
* `POST /sightings/interval` returns a list of sightings within a specific interval
* `POST /sightings/bird/${birdId}` creates a sighting of a specific bird
* `PATCH /sightings/bird/${birdId}/${sightingId}` updates a sighting of a specific bird
* `DELETE /sightings/${id}` deletes a specific sighting


# Fin