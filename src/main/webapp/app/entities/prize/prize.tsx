import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './prize.reducer';

export const Prize = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const prizeList = useAppSelector(state => state.prize.entities);
  const loading = useAppSelector(state => state.prize.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="prize-heading" data-cy="PrizeHeading">
        <Translate contentKey="affliateMarketplaceApp.prize.home.title">Prizes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.prize.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/prize/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.prize.home.createLabel">Create new Prize</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {prizeList && prizeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('prizeType')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.prizeType">Prize Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeType')} />
                </th>
                <th className="hand" onClick={sort('prizeTag')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.prizeTag">Prize Tag</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeTag')} />
                </th>
                <th className="hand" onClick={sort('prizeDetails')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.prizeDetails">Prize Details</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeDetails')} />
                </th>
                <th className="hand" onClick={sort('prizeValueType')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.prizeValueType">Prize Value Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeValueType')} />
                </th>
                <th className="hand" onClick={sort('prizeValue')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.prizeValue">Prize Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeValue')} />
                </th>
                <th className="hand" onClick={sort('countOfPossibleWinners')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.countOfPossibleWinners">Count Of Possible Winners</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('countOfPossibleWinners')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.prize.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.prize.competition">Competition</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {prizeList.map((prize, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/prize/${prize.id}`} color="link" size="sm">
                      {prize.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.PrizeType.${prize.prizeType}`} />
                  </td>
                  <td>{prize.prizeTag}</td>
                  <td>{prize.prizeDetails}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.PrizeValueType.${prize.prizeValueType}`} />
                  </td>
                  <td>{prize.prizeValue}</td>
                  <td>{prize.countOfPossibleWinners}</td>
                  <td>{prize.isActive ? 'true' : 'false'}</td>
                  <td>{prize.createdBy}</td>
                  <td>{prize.createdOn ? <TextFormat type="date" value={prize.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{prize.updatedBy}</td>
                  <td>{prize.updatedOn ? <TextFormat type="date" value={prize.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{prize.competition ? <Link to={`/competition/${prize.competition.id}`}>{prize.competition.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/prize/${prize.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/prize/${prize.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/prize/${prize.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="affliateMarketplaceApp.prize.home.notFound">No Prizes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Prize;
