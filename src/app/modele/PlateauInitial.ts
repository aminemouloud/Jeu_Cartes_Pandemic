export interface PlateauInitial{
    idPartie:string,
    etatPartie:string,
    lesJoueurs:string[],
    lesVilles:string[],
    carteEpidemie:string[],
    cartesPropagation:string[],
    defausse_cartesJoueur:string[],
    defausse_carteDePropagation:string[],
    cartesJoueur:string[],
    nomJoueur:string,
    imageData: {[key: string]: Uint8Array};
    cartes_en_main:string[],
}