# 🏥 Identity Management AL System

## 📌 Project Overview
This module implements the application logic for the external identity management server. This includes the management, creation and distribution of user certificates, the connection to the internal identity module as well as providing the endpoints for the IM UI.

### Member Roles (tbd)
- Beimgraben, Frederik (coding, required reviewer, project structure/guidelines)
- Buyer, Cedric (coding, *architecture*)
- Galán, Julio (coding)
- Díaz, Javier (coding, required reviewer)
- Meyer, Luis (coding, communications, documentation, internal management)

# Guidelines will live **here**

Check if your IDE supports autocorrections regarding the conventions listed below.

## Branch naming scheme

We structure our branch names hierarchically with a forward slash `/` as the delimiter. e.g. `IdentityManagementAL/Backend/SomeNewFeature`.

When doing a fix, we add a `Fix` at the start of the last segment. e.g. `.../FixAPIEndpoints`.

Capitalization should be "CapitalCase" only. No underscores!

## Commit message scheme

We only use the [conventionalcommits.org](https://www.conventionalcommits.org/en/v1.0.0/) standard 

Quick summary:
```txt
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

## Coding conventions

We will use the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

## Communication convention

We will use the signal Group to dicuss upcoming meetings. 
We will have a meeting every week. 
Luis Meyer will be responsible for scheduling.
We will have calendar notifications via email.
Please give feedback.

# Progress

- [ ] User Creation Logic
- [ ] Generate User Certificates
- [ ] User Token Hashes
- [ ] Obtain Token Logic