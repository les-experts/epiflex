function afficher(valeur)
{
    if(document.getElementById(valeur).style.display == 'none')
    {
        document.getElementById(valeur).style.display = 'block';

    }
    else
    {
        document.getElementById(valeur).style.display = 'none';
    }
}
