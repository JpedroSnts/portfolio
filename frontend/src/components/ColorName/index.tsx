interface INameProps {
    fontSize?: Number
    name: [string, string];
}

function Name ({ fontSize, name }: INameProps) {
    return (
        <h1 style={{ fontSize: `${fontSize ?? 18}px` }}>{name[0]} <strong>{name[1]}</strong></h1>
    )
}

export default Name;