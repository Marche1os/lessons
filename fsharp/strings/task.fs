module fsharp.strings.task

// 17.1
let rec pow = function
    | (s, 0) -> ""
    | (s, 1) -> s
    | (s, n) -> s + pow(s, n - 1)

// 17.2
let rec isIthChar = function
    | (s, n, c) when n < 0 || n >= String.length(s) -> false
    | (s, n, c) when s.[n] = c -> true
    | _ -> false

// 17.3
let rec occFromIth = function
    | (s, -1, c) -> 0
    | (s, n, c) when n >= String.length(s) -> 0
    | (s, n, c) when s.[n] = c -> 1 + occFromIth(s, n + 1, c)
    | (s, n, c) -> occFromIth(s, n+1, c)