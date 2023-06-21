# project-links-plugin

Simple IDE plugin for detecting Jira issue links and turning them into a hyperlink for easy navigation.

It allows to resolve
- existing known Open Source Projects
- your internal projects by providing a `.project-links.yml` configuration in your project root folder.

## for-jetbrains

- build it

```bash
cd for-jetbrains
./gradlew build
```

- run it

```bash
cd for-jetbrains
./gradlew runIde
```

## navigator

This is the Open Source Project links redirect, it is used when open source mode is enabled.

- build it

```bash
cd navigator
yarn
```

- run it

```bash
cd navigator
yarn dev
```
