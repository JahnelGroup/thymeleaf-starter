# Flyway Credentials

You should NOT put database credentials in version control and you should NOT pass them in via command line because they can be recalled from the terminal. Instead you should be saving the credentials in a file that is not version controlled as this setup shows you.

The files you place in here are automatically ignored by git so you don't commit them to version control.

You can reference the file like this:

```bash
$ gradle -Dflyway.configFiles=flyway/example.conf,flyway/example.pass flywayInfo
```