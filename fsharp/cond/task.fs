// 16.1
let notDivisible = function
    | (m, 0) -> false
    | (m,n) when m % n = 0 -> true
    | _ -> false

let rec isDivOnSmthExceptOne = function
    | (n, 1) -> true
    | (n, m) when notDivisible(m, n) -> false
    | (n, m) -> isDivOnSmthExceptOne(n, m-1)

// 16.2
let  prime = function
    | 0 | 1 -> false
    | 2 -> true
    | n when notDivisible(2, n) -> false
    | n -> isDivOnSmthExceptOne(n, n-1)