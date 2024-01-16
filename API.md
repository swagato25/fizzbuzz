# FizzBuzz REST Server API

This document describes the API for the FizzBuzz REST Server.

## Endpoints

### GET /fizzbuzz

Returns a list of strings following the FizzBuzz logic.

#### Parameters

- `int1` (integer, default=3): The first integer.
- `int2` (integer, default=5): The second integer.
- `limit` (integer, default=100): The limit for the FizzBuzz sequence.
- `str1` (string, default="fizz"): The first replacement string.
- `str2` (string, default="buzz"): The second replacement string.

#### Response

A JSON array of strings, each representing a number or one of the words "fizz", "buzz", or "fizzbuzz".

### GET /statistics

Returns the parameters of the most used request to the `/fizzbuzz` endpoint.

#### Parameters

None.

#### Response

A JSON object with the following fields:

- `int1` (string): The first integer of the most used request.
- `int2` (string): The second integer of the most used request.
- `limit` (string): The limit of the most used request.
- `str1` (string): The first replacement string of the most used request.
- `str2` (string): The second replacement string of the most used request.
- `count` (string): The number of times the most used request has been made.

## Error Handling

The server returns a 400 Bad Request error if the parameters are not provided or are not in the expected format.
